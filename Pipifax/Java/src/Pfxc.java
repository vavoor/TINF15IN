import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class Pfxc {

  public static void main(String[] args) {

    try {
      CharStream cs = CharStreams.fromFileName(args[0]);
      PipifaxLexer lexer = new PipifaxLexer(cs);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      PipifaxParser parser = new PipifaxParser(tokens);

      ParseTree root = parser.program();
      ASTGenerator gen = new ASTGenerator();
      root.accept(gen);
    }
    catch (Exception e) {
      System.err.println(e);
    }
  }


}
