class ASTVariableDeclaration extends ASTNode {

	private String name;
	private ASTType type;

	public ASTVariableDeclaration(String name, ASTType type) {
		this.name = name;
		this.type = type;
	}

	public String name() {
		return name;
	}
}
