class ASTGenerator extends PipifaxBaseVisitor<ASTNode> {

	public ASTNode visitProgram(PipifaxParser.ProgramContext ctx) {
		ASTProgram program = new ASTProgram();

		for (PipifaxParser.VariableDeclarationContext c : ctx.variableDeclaration()) {
			ASTNode v = c.accept(this);
			program.addGlobalVariable((ASTVariableDeclaration)v);
		}

		for (PipifaxParser.FunctionDefinitionContext c : ctx.functionDefinition()) {
			ASTNode f = c.accept(this);
			program.addFunctionDefinition((ASTFunctionDefinition)f);
		}

		return program;
	}

	public ASTNode visitVariableDeclaration(PipifaxParser.VariableDeclarationContext ctx) {
		ASTType type = (ASTType) ctx.type().accept(this);
		ASTGlobalVariableDeclaration var = new ASTGlobalVariableDeclaration(ctx.ID().getSymbol().getText(),type);
		return var;
	}

	public ASTNode visitFunctionDefinition(PipifaxParser.FunctionDefinitionContext ctx) {
		ASTType type = (ASTType) ctx.type().accept(this);
		ASTFunctionDefinition func = new ASTFunctionDefinition(ctx.ID().getSymbol().getText(),type);
		return func;
	}
}
