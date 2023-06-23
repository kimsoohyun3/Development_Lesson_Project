package project.lesson.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import project.lesson.dto.signin.SignInResponseDto;
import project.lesson.entity.member.Gender;
import project.lesson.entity.member.Member;
import project.lesson.repository.MemberRepository;

@Service
public class OAuthService {

	private final MemberRepository memberRepository;
	private final TokenProvider tokenProvider;

	@Autowired
	public OAuthService(MemberRepository memberRepository, TokenProvider tokenProvider) {
		this.memberRepository = memberRepository;
		this.tokenProvider = tokenProvider;
	}

	public SignInResponseDto kakaoSingIn(String accessToken) {
		//String accessToken = getKakaoAccessToken(accessToken);
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		String email = "";
		//Gender gender = null;
		//access_token을 이용하여 사용자 정보 조회
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + accessToken); //전송할 header 작성, access_token전송

			//결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				throw new IllegalArgumentException("올바른 인가 토큰이 아닙니다.");
			}
			System.out.println("responseCode : " + responseCode);
			System.out.println(conn.getResponseMessage());

			//요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			//Gson 라이브러리로 JSON파싱
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			int id = element.getAsJsonObject().get("id").getAsInt();

			boolean hasEmail = element.getAsJsonObject()
					.get("kakao_account")
					.getAsJsonObject()
					.get("has_email")
					.getAsBoolean();
			if (hasEmail) {
				email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
			}

			/*boolean hasGender = element.getAsJsonObject()
					.get("kakao_account")
					.getAsJsonObject()
					.get("has_gender")
					.getAsBoolean();
			if (hasGender) {
				gender = Gender.valueOf(
						element.getAsJsonObject()
								.get("kakao_account")
								.getAsJsonObject()
								.get("gender")
								.getAsString()
								.toUpperCase()
				);
			}*/

			System.out.println("id : " + id);
			System.out.println("email : " + email);
			//System.out.println("gender : " + gender);

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		Optional<Member> findMember = memberRepository.findByEmail(email);
		if (findMember.isEmpty()) {
			Member createMember = Member.builder()
					.email(email)
					.id(UUID.randomUUID().toString())
					//.gender(gender)
					.build();

			return new SignInResponseDto(tokenProvider.create(memberRepository.save(createMember)));
		} else {
			return new SignInResponseDto(tokenProvider.create(findMember.get()));
		}
	}
}
