package br.com.encibra.encibrateste.domain.dto;

import br.com.encibra.encibrateste.domain.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
