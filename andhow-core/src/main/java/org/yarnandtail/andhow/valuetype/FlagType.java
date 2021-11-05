package org.yarnandtail.andhow.valuetype;

import org.yarnandtail.andhow.util.TextUtil;

/**
 * Metadata and parsing for a Boolean type which is never null, similar to a 'nix flag behavior.
 * <p>
 * This would be used to parse a command line argument that is true by its presence, e.g.:<br>
 * {@code java MyClass launch}<br>
 * If {@code launch} is an AndHow property using the FlagType (i.e. {@code FlagProp}), launch will
 * be considered true just by its presence.
 * <p>
 * This class is threadsafe and uses a singleton pattern to prevent multiple
 * instances since all users can safely use the same instance.
 */
public class FlagType extends BaseValueType<Boolean> {

	private static final FlagType instance = new FlagType();

	protected FlagType() {
		super(Boolean.class);
	}

	/**
	 * @return An instance of the {@link #FlagType()}
	 * @deprecated since 0.4.1. Use {@link #instance()} instead
	 */
	@Deprecated
	public static FlagType get() {
		return instance();
	}

	/**
	 * Fetch the single, shared instace of this ValueType
	 * <p>
	 *
	 * @return An instance of the {@link #FlagType()}
	 */
	public static FlagType instance() {
		return instance;
	}

	@Override
	public Boolean parse(String sourceValue) throws IllegalArgumentException {

		if (sourceValue == null) {
			//Just the presence of the flag is enough to set true
			return true;
		} else {
			return TextUtil.toBoolean(sourceValue);
		}
	}

	@Override
	public Boolean cast(Object o) throws RuntimeException {
		return (Boolean) o;
	}

}
