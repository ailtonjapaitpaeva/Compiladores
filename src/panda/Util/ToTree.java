package panda.Util;

import javaslang.collection.Tree.Node;

public interface ToTree<T> {
   public abstract Node<T> toTree();
}
