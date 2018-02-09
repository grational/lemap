package it.grational.lemap
import org.apache.commons.text.similarity.LevenshteinDistance

/**
 * Fuzzy Map
 *
 * @author d7392
 * @date 09-02-2018 10.06
 * @description a custom map which uses the Apache implementation
 * of the Levenshtein string distance to approximate to the closest
 * matching key. Currently it's a very basic implementation
 */
class FuzzyMap extends AbstractMap {
	private final Map map

	/**
	 * Secondary constructor
	 */
	FuzzyMap() { this([:]) }
	/**
	 * Primary constructor
	 */
	FuzzyMap(Map mp) {
		super()
		this.map = mp
	}

	@Override
	void putAll(Map mp) {
		this.map << mp
	}

	@Override
	Object put(Object key, Object value) {
		this.map << [(key): value]
	}

	@Override
	Set entrySet() {
		this.map.entrySet()
	}

	@Override
	def get(Object key) {
		def stringDistance = new LevenshteinDistance()
		def minStringDistance
		def currentClosedFlag
		this.map.each { k,v ->
			def distance = stringDistance.apply(k,key)
			if ( (minStringDistance == null)
			|| ( distance < minStringDistance ) ) {
				minStringDistance = distance
				currentClosedFlag = v
			}
		}
		currentClosedFlag
	}
}
