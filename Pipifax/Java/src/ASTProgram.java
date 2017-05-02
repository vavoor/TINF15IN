import java.util.HashMap;
import java.util.Map;

class ASTProgram extends ASTNode {
	private Map<String,ASTGloablVariableDeclaration> globalVariables = new HashMap<String,ASTGlobalVariableDeclaration>();
	private Map<String,ASTFunctionDefinition> functions = new HashMap<String,ASTFunctionDefinition>();

	public void addGlobalVariable(ASTGlobalVariableDeclaration var) {
		globalVariables.put(var.name(),var);
	}

	public void addFunctionDefinition(ASTFunctionDefinition func) {
		functions.put(func.name(),func);
	}

}
