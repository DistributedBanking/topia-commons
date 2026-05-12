package io.bitexpress.topia.commons.data.keygenerator;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 * Resolves {@link Type} instances for identifier generators (Hibernate ORM 6+).
 */
final class GeneratorTypes {

    private GeneratorTypes() {
    }

    static Type longIdType(ServiceRegistry serviceRegistry) {
        return null;
    }
}
