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

    // T visitReferenceType(PipifaxParser.ReferenceTypeContext ctx);
    // T visitArrayReferenceType(PipifaxParser.ArrayReferenceTypeContext ctx);

    public ASTNode visitIntType(PipifaxParser.IntTypeContext ctx) {
    }

    // T visitDoubleType(PipifaxParser.DoubleTypeContext ctx);
    // T visitStringType(PipifaxParser.StringTypeContext ctx);
    // T visitArrayType(PipifaxParser.ArrayTypeContext ctx);
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