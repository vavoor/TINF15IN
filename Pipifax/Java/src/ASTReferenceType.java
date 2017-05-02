class ASTReferenceType extends ASTType {
	private ASTType baseType;

	public ASTReferenceType(ASTType baseType) {
		this.baseType = baseType;
	}
}
