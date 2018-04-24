package org.carlos_witek.back_to_the_future_ii;

import java.util.List;

import org.carlos_witek.back_to_the_future_ii.model.Contest;
import org.carlos_witek.back_to_the_future_ii.model.ContestPrize;

public interface Settlement {

	List<ContestPrize> settle( Contest contest );
}
