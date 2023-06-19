package project.lesson.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import project.lesson.entity.member.Member;
import project.lesson.entity.member.Role;

@Service
public class TokenProvider {
	private static final String SECRET_KEY = "NMA8JpctFuna59f5";

	public String create(Member member) {
		Date expiryDate = new Date(Instant.now().plusSeconds(3000).toEpochMilli());
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.setSubject(member.getId())
				.claim("role", member.getRole().toString())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(expiryDate)
				.compact();

	}

	public String validateAndGetUserId(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();

		return claims.getSubject();
	}

	public String validateAndGetRole(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();

		return (String)claims.get("role");
	}

}
