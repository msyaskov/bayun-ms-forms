package dev.bayun.ms.forms.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Максим Яськов
 */
public class AuthenticatedPrincipalBuilder {

    private UUID id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String pictureUrl;

    private String passwordHash;

    private boolean expired = false;
    private boolean locked = false;
    private boolean credentialsExpired = false;
    private boolean enabled = true;

    private final Map<String, Object> attributes = new HashMap<>();
    private final Collection<GrantedAuthority> authorities = new HashSet<>();

    AuthenticatedPrincipalBuilder() {

    }

    public AuthenticatedPrincipalBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public AuthenticatedPrincipalBuilder id(Supplier<UUID> supplier) {
        this.id = supplier.get();
        return this;
    }

    public AuthenticatedPrincipalBuilder email(String email) {
        this.email = email;
        return this;
    }

    public AuthenticatedPrincipalBuilder email(Supplier<String> supplier) {
        this.email = supplier.get();
        return this;
    }

    public AuthenticatedPrincipalBuilder username(String username) {
        this.username = username;
        return this;
    }

    public AuthenticatedPrincipalBuilder username(Supplier<String> supplier) {
        this.username = supplier.get();
        return this;
    }

    public AuthenticatedPrincipalBuilder passwordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public AuthenticatedPrincipalBuilder passwordHash(Supplier<String> supplier) {
        this.passwordHash = supplier.get();
        return this;
    }

    public AuthenticatedPrincipalBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AuthenticatedPrincipalBuilder firstName(Supplier<String> supplier) {
        this.firstName = supplier.get();
        return this;
    }

    public AuthenticatedPrincipalBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AuthenticatedPrincipalBuilder lastName(Supplier<String> supplier) {
        this.lastName = supplier.get();
        return this;
    }

    public AuthenticatedPrincipalBuilder pictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public AuthenticatedPrincipalBuilder pictureUrl(Supplier<String> supplier) {
        this.pictureUrl = supplier.get();
        return this;
    }

    public AuthenticatedPrincipalBuilder expired(boolean expired) {
        this.expired = expired;
        return this;
    }

    public AuthenticatedPrincipalBuilder expired(Supplier<Boolean> supplier) {
        this.expired = supplier.get();
        return this;
    }

    public AuthenticatedPrincipalBuilder locked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public AuthenticatedPrincipalBuilder locked(Supplier<Boolean> supplier) {
        this.locked = supplier.get();
        return this;
    }

    public AuthenticatedPrincipalBuilder credentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
        return this;
    }

    public AuthenticatedPrincipalBuilder credentialsExpired(Supplier<Boolean> supplier) {
        this.credentialsExpired = supplier.get();
        return this;
    }

    public AuthenticatedPrincipalBuilder enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public AuthenticatedPrincipalBuilder enabled(Supplier<Boolean> supplier) {
        this.enabled = supplier.get();
        return this;
    }

    public AuthenticatedPrincipalBuilder attributes(String key, Object value) {
        this.attributes.put(key, value);
        return this;
    }

    public AuthenticatedPrincipalBuilder attributes(Consumer<Map<String, Object>> consumer) {
        consumer.accept(this.attributes);
        return this;
    }

    public AuthenticatedPrincipalBuilder authority(GrantedAuthority authority) {
        this.authorities.add(authority);
        return this;
    }

    public AuthenticatedPrincipalBuilder authorities(Consumer<Collection<GrantedAuthority>> consumer) {
        consumer.accept(this.authorities);
        return this;
    }

    public AuthenticatedPrincipal build() {
        return new AuthenticatedPrincipal(
                id, username, passwordHash, email, firstName, lastName, pictureUrl,
                expired, locked, credentialsExpired, enabled, attributes, authorities);
    }
}
