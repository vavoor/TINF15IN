class ASTArrayType extends ASTType {

	private ASTType baseType;
	private int size;

	public ASTArrayType(int size, ASTType baseType) {
		this.size = size;
		this.baseType = baseType;
	}
}
