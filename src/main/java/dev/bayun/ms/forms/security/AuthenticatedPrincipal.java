package dev.bayun.ms.forms.security;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.Assert;

import java.util.*;

@Getter
public class AuthenticatedPrincipal implements UserDetails, OAuth2User, CredentialsContainer {

    @Getter
    private final UUID id;

    @Getter
    private final String username;

    @Getter
    private String password;

    @Getter
    private final String email;

    @Getter
    private final String firstName;

    @Getter
    private final String lastName;

    @Getter
    private final String pictureUrl;

    @Getter(AccessLevel.NONE)
    private final boolean expired;

    @Getter(AccessLevel.NONE)
    private final boolean locked;

    @Getter(AccessLevel.NONE)
    private final boolean credentialsExpired;

    private final boolean enabled;

    @Getter
    private final Map<String, Object> attributes;

    @Getter
    private final Collection<GrantedAuthority> authorities;

    public AuthenticatedPrincipal(UUID id, String username, String password, String email,
                                  String firstName, String lastName, String pictureUrl,
                                  boolean expired, boolean locked, boolean credentialsExpired, boolean enabled,
                                  Map<String, Object> attributes, Collection<GrantedAuthority> authorities) {

        Assert.notNull(id, "a id must not be null");
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;

        this.password = password;

        this.expired = expired;
        this.locked = locked;
        this.credentialsExpired = credentialsExpired;
        this.enabled = enabled;

        this.attributes = Collections.unmodifiableMap(Objects.requireNonNullElse(attributes, Collections.emptyMap()));
        this.authorities = Collections.unmodifiableCollection(Objects.requireNonNullElse(authorities, Collections.emptySet()));
    }

    public static AuthenticatedPrincipalBuilder builder() {
        return new AuthenticatedPrincipalBuilder();
    }

    @Override
    public String getName() {
        return this.id.toString();
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AuthenticatedPrincipal principal = (AuthenticatedPrincipal) o;

        return Objects.equals(this.getId(), principal.getId())
                && Objects.equals(this.getUsername(), principal.getUsername())
                && Objects.equals(this.getPassword(), principal.getPassword())
                && Objects.equals(this.getEmail(), principal.getEmail())
                && Objects.equals(this.getAuthorities(), principal.getAuthorities())
                && Objects.equals(this.isAccountNonExpired(), principal.isAccountNonExpired())
                && Objects.equals(this.isAccountNonLocked(), principal.isAccountNonLocked())
                && Objects.equals(this.isCredentialsNonExpired(), principal.isCredentialsNonExpired())
                && Objects.equals(this.isEnabled(), principal.isEnabled())
                && Objects.equals(this.getFirstName(), principal.getFirstName())
                && Objects.equals(this.getLastName(), principal.getLastName())
                && Objects.equals(this.getPictureUrl(), principal.getPictureUrl())
                && Objects.equals(this.getAttributes(), principal.getAttributes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getEmail(), getAuthorities(),
                isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled(),
                getFirstName(), getLastName(), getPictureUrl(), getAttributes());
    }

    @Override
    public String toString() {
        return "AuthenticatedPrincipal(" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", password=HIDDEN" +
                ", email='" + getEmail() + '\'' +
                ", authorities=" + getAuthorities() +
                ", accountNonExpired=" + isAccountNonExpired() +
                ", accountNonLocked=" + isAccountNonLocked() +
                ", credentialsNonExpired=" + isCredentialsNonExpired() +
                ", enabled=" + isEnabled() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", pictureUrl='" + getPictureUrl() + '\'' +
                ", attributes=" + getAttributes() + ')';
    }
}
