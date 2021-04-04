package com.minichat.attractor.user;


import com.minichat.attractor.validation.FieldMatch;
import com.minichat.attractor.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message =
			"The password fields must match")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrmUser {

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String userName;

}
