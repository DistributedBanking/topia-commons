package io.bitexpress.topia.commons.basic.competition;

import java.util.Date;

public interface CompetitionCallback<T> {

	T won(Date executeTime);

}
