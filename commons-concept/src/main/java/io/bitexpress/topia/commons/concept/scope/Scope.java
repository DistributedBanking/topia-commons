package io.bitexpress.topia.commons.concept.scope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Scope<T extends Serializable> implements Serializable {

    @Valid
    protected T from;

    @Valid
    protected T to;

}
