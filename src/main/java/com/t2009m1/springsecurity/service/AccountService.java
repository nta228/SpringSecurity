package com.t2009m1.springsecurity.service;

import com.auth0.jwt.JWT;
import com.t2009m1.springsecurity.entity.Account;
import com.t2009m1.springsecurity.entity.Credential;
import com.t2009m1.springsecurity.entity.dto.AccountLoginDto;
import com.t2009m1.springsecurity.entity.dto.AccountRegisterDto;
import com.t2009m1.springsecurity.repository.AccountRepository;
import com.t2009m1.springsecurity.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    final AccountRepository accountRepository;
    final PasswordEncoder passwordEncoder;

    public AccountRegisterDto register(AccountRegisterDto accountRegisterDto) {
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(accountRegisterDto.getUsername());
        if (optionalAccount.isPresent()) {
            return null;
        }
        System.out.println("Password " + accountRegisterDto.getPassword());
        Account account = Account.builder()
                .username(accountRegisterDto.getUsername())
                .role(accountRegisterDto.getRole())
                .password(passwordEncoder.encode(accountRegisterDto.getPassword()))
                .build();
        accountRepository.save(account);
        AccountRegisterDto accountDto = AccountRegisterDto.builder()
                .username(account.getUsername())
                .role(account.getRole())
                .id(account.getId())
                .build();
        return accountDto;
    }

    public Credential login(AccountLoginDto accountLoginDto) {
        User user = (User) loadUserByUsername(accountLoginDto.getUsername());
        boolean isMatched = passwordEncoder.matches(accountLoginDto.getPassword(), user.getPassword());
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(accountLoginDto.getUsername());
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            int expiredAfterDay = 7;
            String accessToken = JwtUtil.generateTokenV2(account, expiredAfterDay * 24 * 60 * 60 * 60);
            String refreshToken = JwtUtil.generateTokenV2(account, 14 * 24 * 60 * 60 * 60);
            return Credential.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .expiredAt(System.currentTimeMillis() + expiredAfterDay * 24 * 60 * 60 * 60)
                    .scope("basic_account_info")
                    .build();
        }
        throw  new UsernameNotFoundException("User not found");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(username);
        if (!optionalAccount.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        Account account = optionalAccount.get();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(account.getRole() == 1 ? "ADMIN" : "USER");
        grantedAuthorityList.add(simpleGrantedAuthority);
        return new User(account.getUsername(), account.getPassword(), grantedAuthorityList);
    }
}