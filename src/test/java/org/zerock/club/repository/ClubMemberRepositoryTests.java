package org.zerock.club.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClubMemberRepositoryTests {
    @Autowired
    private ClubMemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            ClubMember member = ClubMember.builder()
                    .email("user"+i+"@aaa.com")
                    .name("사용자"+i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            member.addMemeberRole(ClubMemberRole.USER);

            if(i > 80) {
                member.addMemeberRole(ClubMemberRole.MANAGER);
            }

            if(i > 90) {
                member.addMemeberRole(ClubMemberRole.ADMIN);
            }

            repository.save(member);
        });
    }

    @Test
    public void testRead() {
        ClubMember member = repository.findByEmail("user95@aaa.com", false).orElseThrow();

        System.out.println(member);
    }
}