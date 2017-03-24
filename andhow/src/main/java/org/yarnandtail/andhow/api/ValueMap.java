package org.yarnandtail.andhow.api;

/**
 * The values that are loaded or otherwise specified for a set of Properties
 * in a AndHow.
 * 
 * This is basically a dedicated Map with Property keys and their associated
 * values.  Not all Properties will be in the map, only the ones which have a
 * value specified.  During runtime, an immutable instance of this class contains
 * all the needed information to map Properties to their values.
 * 
 * @author eeverman
 */
public interface ValueMap {
	/**
	 * Get a value explicitly configured.
	 * 
	 * If not configured via one of the Loaders, null is returned, not the default
	 * value.
	 * 
	 * @param <T> prop The property to get the value for
	 * @return The value, if explicitly set, or null if not explicity set.
	 */
	<T> T getExplicitValue(Property<T> prop);
	
	/**
	 * The explicitly value, or if that is null, the default (which may also be null).
	 * 
	 * @param prop The property to get the value for.
	 * @return The explicit value or, if no explicit, the default value.  Otherwise null.
	 */
	<T> T  getEffectiveValue(Property<T> prop);
	
	/**
	 * Returns true if the Property's value was explicitly set via one of the loaders.
	 * 
	 * At the moment, that means it would also return a non-null value, however,
	 * future versions may support explicit nulls.
	 * 
	 * @param prop The property to check
	 * @return True if this value is explicitly set.
	 */
	boolean isExplicitlySet(Property<?> prop);
}
