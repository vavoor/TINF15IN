class ASTGenerator extends PipifaxBaseVisitor<ASTNode> {

    public ASTNode visitProgram(PipifaxParser.ProgramContext ctx) {
        ASTProgram program = new ASTProgram();

        for (PipifaxParser.GlobalVariableDeclarationContext c : ctx.globalVariableDeclaration()) {
            ASTNode v = c.accept(this);
            program.addGlobalVariable((ASTGlobalVariableDeclaration)v);
        }

        for (PipifaxParser.FunctionDefinitionContext c : ctx.functionDefinition()) {
            ASTNode f = c.accept(this);
            program.addFunctionDefinition((ASTFunctionDefinition)f);
        }

        return program;
    }

    public ASTNode visitGlobalVariableDeclaration(PipifaxParser.GlobalVariableDeclarationContext ctx) {
        ASTType type = (ASTType) ctx.type().accept(this);
        ASTGlobalVariableDeclaration var = new ASTGlobalVariableDeclaration(ctx.ID().getSymbol().getText(),type);
        return var;
    }

    public ASTNode visitFunctionDefinition(PipifaxParser.FunctionDefinitionContext ctx) {
    	String namen = ctx.ID().getSymbol().getText();
        ASTType type = (ASTType) ctx.type().accept(this);
        AST
        ASTFunctionDefinition func = new ASTFunctionDefinition(name,type);
        return func;
    }

    public ASTNode visitIntType(PipifaxParser.IntTypeContext ctx) {
    	return new ASTIntType();
    }

    public ASTNode visitDoubleType(PipifaxParser.DoubleTypeContext ctx) {
    	return new ASTDoubleType();
    }

    public ASTNode visitStringType(PipifaxParser.StringTypeContext ctx) {
    	return new ASTStringType();
    }

    public ASTNode visitArrayType(PipifaxParser.ArrayTypeContext ctx) {
    	int size = Integer.parseInt(ctx.INT_LITERAL().getSymbol().getText());
    	ASTType t = (ASTType) ctx.type().accept(this);
    	ASTArrayType type = new ASTArrayType(size,t);
    	return type;
    }

    public ASTNode visitReferenceType(PipifaxParser.ReferenceTypeContext ctx) {
    	ASTType baseType = (ASTType) ctx.type().accept(this);
    	ASTReferenceType type = new ASTReferenceType(baseType);
    	return type;
    }

    public ASTNode visitArrayReferenceType(PipifaxParser.ArrayReferenceTypeContext ctx) {
    	ASTType baseType = (ASTType) ctx.type().accept(this);
    	ASTReferenceType type = new ASTReferenceType(new ASTArrayType(0,baseType));
    	return type;
    }

    // T visitParameterList(PipifaxParser.ParameterListContext ctx);
    // T visitParameter(PipifaxParser.ParameterContext ctx);
    // T visitTheType(PipifaxParser.TheTypeContext ctx);

    // T visitBlock(PipifaxParser.BlockContext ctx);
    // T visitStatement(PipifaxParser.StatementContext ctx);
    // T visitAssignment(PipifaxParser.AssignmentContext ctx);
    // T visitIfStatement(PipifaxParser.IfStatementContext ctx);
    // T visitWhileStatement(PipifaxParser.WhileStatementContext ctx);
    // T visitFunctionCallStatement(PipifaxParser.FunctionCallStatementContext ctx);
    // T visitIndexedLValue(PipifaxParser.IndexedLValueContext ctx);
    // T visitIdLValue(PipifaxParser.IdLValueContext ctx);
    // T visitFunctionCall(PipifaxParser.FunctionCallContext ctx);
    // T visitExprList(PipifaxParser.ExprListContext ctx);
    // T visitVarExpr(PipifaxParser.VarExprContext ctx);
    // T visitDoubleLiteralExpr(PipifaxParser.DoubleLiteralExprContext ctx);
    // T visitEqExpr(PipifaxParser.EqExprContext ctx);
    // T visitUnaryExpr(PipifaxParser.UnaryExprContext ctx);
    // T visitStringLiteralExpr(PipifaxParser.StringLiteralExprContext ctx);
    // T visitIntLiteralExpr(PipifaxParser.IntLiteralExprContext ctx);
    // T visitAddExpr(PipifaxParser.AddExprContext ctx);
    // T visitBracketExpr(PipifaxParser.BracketExprContext ctx);
    // T visitCompExpr(PipifaxParser.CompExprContext ctx);
    // T visitOrExpr(PipifaxParser.OrExprContext ctx);
    // T visitMultExpr(PipifaxParser.MultExprContext ctx);
    // T visitAndExpr(PipifaxParser.AndExprContext ctx);
    // T visitFunctionCallValue(PipifaxParser.FunctionCallValueContext ctx);
    // T visitIndexedValue(PipifaxParser.IndexedValueContext ctx);
    // T visitIdValue(PipifaxParser.IdValueContext ctx);
}