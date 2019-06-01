/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.ast;

import java.util.Iterator;


/**
 * Represents either a {@code case} or {@code default} label inside
 * a {@linkplain ASTSwitchStatement switch statement} or {@linkplain ASTSwitchExpression expression}.
 * Since Java 12, labels may have several expressions.
 *
 * <pre class="grammar">
 *
 * SwitchLabel ::=  "case" {@linkplain ASTExpression Expression} ("," {@linkplain ASTExpression Expression} )*
 *                | "default"
 *
 * </pre>
 */
public final class ASTSwitchLabel extends AbstractJavaNode implements Iterable<ASTExpression> {

    private boolean isDefault;


    ASTSwitchLabel(int id) {
        super(id);
    }


    ASTSwitchLabel(JavaParser p, int id) {
        super(p, id);
    }

    void setDefault() {
        isDefault = true;
    }

    public boolean isDefault() {
        return isDefault;
    }

    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }

    @Override
    public Iterator<ASTExpression> iterator() {
        return new NodeChildrenIterator<>(this, ASTExpression.class);
    }
}
