package org.carlos_witek.back_to_the_future_ii.model;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

public class Contest {
	private final String name;
	private final List<BigDecimal> prizes;
	private final List<ContestEntry> entries;

	public Contest( final String name, final List<BigDecimal> prizes,
			final List<ContestEntry> entries ) {
		this.name = name;
		this.prizes = ImmutableList.copyOf( prizes );
		this.entries = ImmutableList.copyOf( entries );
	}

	public String getName() {
		return name;
	}

	public List<BigDecimal> getPrizes() {
		return prizes;
	}

	public List<ContestEntry> getEntries() {
		return entries;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper( this )
				.add( "id", name )
				.add( "prizes", prizes )
				.add( "entries", entries )
				.toString();
	}
}
