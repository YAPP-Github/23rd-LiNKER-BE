package com.imlinker.storage.contacts;

import com.imlinker.storage.common.converters.JdbcConverterRegistry;
import com.imlinker.storage.common.converters.SecureStringConverter;
import com.imlinker.storage.common.model.SecureString;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.jdbc.core.RowMapper;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "contacts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ContactsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref_user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    @Convert(converter = SecureStringConverter.class)
    private SecureString phoneNumber;

    @Column(name = "email")
    @Convert(converter = SecureStringConverter.class)
    private SecureString email;

    @Column(name = "school")
    private String school;

    @Column(name = "careers")
    private String careers;

    @Column(name = "profile_img_url")
    private String profileImgUrl;

    @Lob
    @Column(name = "description")
    private String description;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static RowMapper<ContactsEntity> getRowMapper() {
        SecureStringConverter secureStringConverter =
                (SecureStringConverter) JdbcConverterRegistry.getConverter(SecureString.class);
        return ((rs, rowNum) ->
                new ContactsEntity(
                        rs.getLong("id"),
                        rs.getLong("ref_user_id"),
                        rs.getString("name"),
                        secureStringConverter.convertToEntityAttribute(rs.getString("phone_number")),
                        secureStringConverter.convertToEntityAttribute(rs.getString("email")),
                        rs.getString("school"),
                        rs.getString("careers"),
                        rs.getString("profile_img_url"),
                        rs.getString("description"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class)));
    }
}
