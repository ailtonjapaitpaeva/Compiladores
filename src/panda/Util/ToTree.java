package panda.Util;


import javaslang.collection.Tree.Node;

public interface ToTree<E> {
   public abstract Node<E> toTree();
}
