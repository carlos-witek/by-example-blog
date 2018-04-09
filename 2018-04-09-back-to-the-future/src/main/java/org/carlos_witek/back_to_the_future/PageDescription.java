package org.carlos_witek.back_to_the_future;

import com.google.common.base.MoreObjects;

public class PageDescription {

	private long startIndex;
	private int limit;

	public PageDescription() {
		this( 0, 100 );
	}

	public PageDescription( long startIndex, int limit ) {
		this.startIndex = startIndex;
		this.limit = limit;
	}

	public long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex( long startIndex ) {
		this.startIndex = startIndex;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit( int limit ) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper( this )
				.add( "startIndex", startIndex )
				.add( "limit", limit )
				.toString();
	}
}
