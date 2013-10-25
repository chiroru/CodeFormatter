package jp.ddo.chiroru.codeformatter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.TagElement;

/**
 * サンプルクラス.
 * @author z2010192
 *
 */
public class SourceReader {

	private final String source;

	public SourceReader(String source) {
		this.source = source;
	}

	public void read() {
		System.out.println("read() - start");

		ASTParser parser = ASTParser.newParser(AST.JLS3);
		// parser.setResolveBindings(true);
		parser.setSource(source.toCharArray());

		CompilationUnit unit = (CompilationUnit) parser
				.createAST(new NullProgressMonitor());

		unit.accept(new ASTVisitorImpl());

//		System.out.println("ファイル名: " + element.getElementName());// ファイル名
//		String sourceName = element.getElementName().substring(0,
//				element.getElementName().lastIndexOf("."));
//		System.out.println("クラス名: " + sourceName);
//		System.out.println("クラスの完全修飾クラス名: "
//				+ element.getType(sourceName).getFullyQualifiedName());
//		System.out.println("パッケージ名: " + element.getParent().getElementName());
//
//		System.out.println("read() - end");
	}

	class ASTVisitorImpl extends ASTVisitor {

		public boolean visit(Javadoc node) {
			System.out.println("visit(Javadoc) - start");
			System.out.println(node);
			Iterator iterator = node.tags().iterator();
			while (iterator.hasNext()) {
				TagElement element = (TagElement) iterator.next();
				System.out.println("型:" + element.getClass().getName());
				System.out.println("tagname: " + element.getTagName());
				System.out.println(element);
			}
			System.out.println("visit(Javadoc) - end");
			return super.visit(node);
		}
	}

	public static void main(String[] args) {
//		if (args.length != 1) {
//			System.out.println("File is not defined.");
//			System.exit(1);
//		}
		try {
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream("C:\\Users\\z2010192\\workspace\\CodeFormatter\\src\\main\\java\\jp\\ddo\\chiroru\\codeformatter\\SourceReader.java")));
			String line;
			while( (line = br.readLine()) != null ){
				sb.append(line+"\n");
			}


			SourceReader reader = new SourceReader(sb.toString());
			reader.read();

			System.out.println("Done !");
		} catch ( FileNotFoundException e) {
			System.out.println("File not found.");
			return;
		} catch ( IOException e ){
			System.out.println("IO Exception !");
			return;
		}
	}
}