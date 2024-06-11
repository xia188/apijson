package apijson.demo;

import apijson.framework.APIJSONCreator;
import apijson.orm.FunctionParser;
import apijson.orm.Parser;
import apijson.orm.SQLConfig;
import apijson.orm.SQLExecutor;
import apijson.orm.Verifier;

public class DemoCreator extends APIJSONCreator<Long> {

	@Override
	public SQLConfig<Long> createSQLConfig() {
		return new DemoSQLConfig();
	}

	@Override
	public SQLExecutor<Long> createSQLExecutor() {
		return new DemoSQLExecutor();
	}

	@Override
	public FunctionParser<Long> createFunctionParser() {
		return new DemoFunctionParser();
	}

	@Override
	public Parser<Long> createParser() {
		return new DemoParser();
	}

	@Override
	public Verifier<Long> createVerifier() {
		return new DemoVerifier();
	}
}
