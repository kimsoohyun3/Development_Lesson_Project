package project.lesson.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import project.lesson.dto.authmail.AuthMailRequestDto;
import project.lesson.dto.authmail.AuthMailResponseDto;

@Service
public class AuthMailService {
	private final int CERTIFICATION_KEY_LENGTH = 8;
	private final JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String mailSenderAddr;

	@Autowired
	public AuthMailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	private MimeMessage createMessage(String to, String certificationKey) throws
			MessagingException,
			UnsupportedEncodingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.addRecipients(Message.RecipientType.TO, to);
		message.setSubject("과외구인사이트 회원가입 이메일 인증");

		String content = "";
		content += "<div style=\\\"background: #fff; margin: 0; padding: 4rem 0; max-width: 600px;\\\">";
		content += "<div style=\\\"background: #fff; margin: 0; padding: 4rem 0; max-width: 600px;\\\">";
		content += "<div>";
		content +=
				"<p style=\\\"margin: 0 0 1.5rem 0; padding: 0; font-size: 1.5rem; color: #000; line-height: 2rem; font-weight: bold;\\\">";
		content += "이메일 인증을 진행해주세요";

		content += "</p>";
		content +=
				"<p style=\\\"margin: 0 0 1.5rem 0; padding: 0; font-size: 1rem; color: #404040; line-height: 1.5rem;\\\">";
		content += "회원가입을 위해 아래 인증번호를 화면에 입력해주세요.";
		content += "</p>";
		content += "</div>";
		content +=
				"<div style=\\\"background: #f9f9fb; border: 1px solid #ececec; border-radius: 4px;\\\">";
		content +=
				"<table style=\\\"width: 100%; display: table; border-collapse: separate; box-sizing: border-box; text-indent: initial; border-spacing: 2px; text-align: center;\\\">";

		content += "<tbody>";
		content += "<tr>";
		content += "<td style=\\\"font-size: 3rem; color: #202020; letter-spacing: 0.5rem; padding-block: 2rem;\\\">";
		content += "인증번호 : " + certificationKey;

		content += "</td>";
		content += "</tr>";
		content += "</tbody>";
		content += "</table>";
		content += "</div>";
		content += "</div>";

		message.setText(content, "UTF-8", "html");
		message.setFrom(new InternetAddress(mailSenderAddr, "과외구인사이트"));
		return message;
	}

	private String createCertificationKey() {
		StringBuilder certificationKey = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < CERTIFICATION_KEY_LENGTH; i++) {
			int index = random.nextInt(3);
			switch (index) {
				case 0:
					certificationKey.append((char)(random.nextInt(26) + 97));
					break;
				case 1:
					certificationKey.append((char)(random.nextInt(26) + 65));
					break;
				case 2:
					certificationKey.append(random.nextInt(10));
					break;
			}
		}
		return certificationKey.toString();
	}

	public AuthMailResponseDto sendAuthMail(AuthMailRequestDto authMailRequestDto) throws
			MessagingException,
			UnsupportedEncodingException {
		String certificationKey = createCertificationKey();
		MimeMessage message = createMessage(authMailRequestDto.getEmail(), certificationKey);
		javaMailSender.send(message);
		return new AuthMailResponseDto(certificationKey);
	}
}
