package io.bitexpress.topia.commons.concept.scope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Scope<T> {

	@Valid
    private T from;

	@Valid
    private T to;

}
