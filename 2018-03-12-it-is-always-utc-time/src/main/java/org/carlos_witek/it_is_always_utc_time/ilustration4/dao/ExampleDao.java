package org.carlos_witek.it_is_always_utc_time.ilustration4.dao;

public interface ExampleDao {

	ExampleEntity findExample( Long id );

	Long createExample( String value );

	Long updateExample( Long id, String value );

}
