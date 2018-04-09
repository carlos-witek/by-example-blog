package org.carlos_witek.back_to_the_future;

import java.util.List;

import com.google.common.base.MoreObjects;

public class Page<T> {

	private PageDescription description;
	private List<T> elements;
	private long totalCount;

	public Page( final List<T> elements, final long totalCount,
			final PageDescription description ) {
		this.elements = elements;
		this.totalCount = totalCount;
		this.description = description;
	}

	public PageDescription getDescription() {
		return description;
	}

	public void setDescription( final PageDescription description ) {
		this.description = description;
	}

	public List<T> getElements() {
		return elements;
	}

	public void setElements( final List<T> elements ) {
		this.elements = elements;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount( final long totalCount ) {
		this.totalCount = totalCount;
	}

	public PageDescription nextPageDescription() {
		final long nextStartIndex = description.getStartIndex() + description.getLimit();

		if ( nextStartIndex < totalCount )
			return new PageDescription( nextStartIndex, description.getLimit() );
		else
			return null;
	}

	public PageDescription previousPageDescription() {
		final long previousStartIndex = description.getStartIndex() - description.getLimit();

		if ( 0 <= previousStartIndex )
			return new PageDescription( previousStartIndex, description.getLimit() );
		else
			return null;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper( this )
				.add( "description", description )
				.add( "elementsCount", elements.size() )
				.add( "totalCount", totalCount )
				.toString();
	}
}
