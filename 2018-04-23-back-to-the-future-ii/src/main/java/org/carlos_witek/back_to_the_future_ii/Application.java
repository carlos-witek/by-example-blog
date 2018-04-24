package org.carlos_witek.back_to_the_future_ii;

import java.util.ArrayList;
import java.util.List;

import org.carlos_witek.back_to_the_future_ii.impl.SettlementDefault;
import org.carlos_witek.back_to_the_future_ii.model.Contest;
import org.carlos_witek.back_to_the_future_ii.model.ContestPrize;

public class Application {

	public static void main( String[] args ) {

		final Settlement settlement = provideSettlement( args );

		List<Contest> contests = new ArrayList<>();
		for ( Contest contest : contests ) {

			List<ContestPrize> settle = settlement.settle( contest );
		}

	}

	private static Settlement provideSettlement( String[] args ) {
		return new SettlementDefault();
	}

}
