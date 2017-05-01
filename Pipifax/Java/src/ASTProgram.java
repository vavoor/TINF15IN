import java.util.HashMap;
import java.util.Map;

class ASTProgram extends ASTNode {
	private Map<String,ASTVariableDeclaration> globalVariables = new HashMap<String,ASTVariableDeclaration>();
	private Map<String,ASTFunctionDefinition> functions = new HashMap<String,ASTFunctionDefinition>();

	public void addGlobalVariable(ASTVariableDeclaration var) {
		globalVariables.put(var.name(),var);
	}

	public void addFunctionDefinition(ASTFunctionDefinition func) {
		functions.put(func.name(),func);
	}

}
