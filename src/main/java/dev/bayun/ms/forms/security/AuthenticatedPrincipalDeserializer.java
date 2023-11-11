package dev.bayun.ms.forms.security;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.NullNode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Максим Яськов
 */

public class AuthenticatedPrincipalDeserializer extends JsonDeserializer<AuthenticatedPrincipal> {

    @Override
    public AuthenticatedPrincipal deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);

        return AuthenticatedPrincipal.builder()
                .id(() -> UUID.fromString(readJsonNode(jsonNode, "id").asText(null)))
                .username(readJsonNode(jsonNode, "username").asText(null))
                .passwordHash(readJsonNode(jsonNode, "password").asText(""))
                .email(readJsonNode(jsonNode, "email").asText(null))
                .firstName(readJsonNode(jsonNode, "firstName").asText(null))
                .lastName(readJsonNode(jsonNode, "lastName").asText(null))
                .pictureUrl(readJsonNode(jsonNode, "pictureUrl").asText(null))
                .expired(readJsonNode(jsonNode, "expired").asBoolean(false))
                .locked(readJsonNode(jsonNode, "locked").asBoolean(false))
                .credentialsExpired(readJsonNode(jsonNode, "credentialsExpired").asBoolean(false))
                .enabled(readJsonNode(jsonNode, "enabled").asBoolean(true))
                .attributes(attributes -> {
                    JsonNode node = readJsonNode(jsonNode, "attributes");
                    if (!node.isArray()) {
                        return;
                    }

                    node.forEach(entry -> {
                        try {
                            JsonNode keyNode = entry.get(0);
                            JsonNode valueNode = entry.get(1);
                            if (keyNode.equals(NullNode.getInstance()) || valueNode.equals(NullNode.getInstance())) {
                                return;
                            }

                            attributes.put(keyNode.asText(), valueNode.asText());
                        } catch (Exception ignored) {
                            // skip entry
                        }
                    });
                })
                .authorities(authorities -> {
                    JsonNode node = readJsonNode(jsonNode, "authorities");
                    if (!node.isArray()) {
                        return;
                    }

                    node.forEach(entry -> {
                        try {
                            authorities.add(new SimpleGrantedAuthority(entry.asText()));
                        } catch (Exception ignored) {
                            // skip entry
                        }
                    });
                })
                .build();
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

}
