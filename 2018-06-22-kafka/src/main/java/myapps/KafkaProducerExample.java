package myapps;

import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducerExample {

	private final static String TOPIC = "streams-plaintext-input";

	private static Producer<Long, String> createProducer() {
		Properties props = new Properties();
		props.put( ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer" );
		props.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092" );
		props.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName() );
		props.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName() );

		return new KafkaProducer<>( props );
	}

	static void runProducer( final int sendMessageCount ) throws Exception {
		final Producer<Long, String> producer = createProducer();

		long time = System.currentTimeMillis();

		try {
			for ( long index = time; index < time + sendMessageCount; index++ ) {
				final ProducerRecord<Long, String> record = new ProducerRecord<>( TOPIC,
						index,
						randomSubstring() );

				RecordMetadata metadata = producer.send( record ).get();

				long elapsedTime = System.currentTimeMillis() - time;
				System.out.printf(
						"sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d) time=%d\n",
						record.key(), record.value(), metadata.partition(), metadata.offset(),
						elapsedTime );
			}
		} finally {
			producer.flush();
			producer.close();
		}
	}

	private static String randomSubstring() {
		final String string = "A stream is the most important abstraction provided by Kafka Streams: it represents an unbounded, continuously updating data set.";

		final Random random = new Random();
		final int beginIndex = random.nextInt( string.length() );
		final int endIndex = random.nextInt( string.length() - beginIndex );

		return string.substring( beginIndex, beginIndex + endIndex );
	}

	public static void main( String... args ) throws Exception {
		if ( args.length == 0 ) {
			runProducer( 5 );
		} else {
			runProducer( Integer.parseInt( args[0] ) );
		}
	}
}
