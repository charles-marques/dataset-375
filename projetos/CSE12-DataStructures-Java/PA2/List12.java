import java.util.*;

public class List12<E> implements java.util.List<E>
{
   private Node<E> headNode, prevNode, nextNode, selectedNode;
   private boolean addSuccess;
   private int numNodes, eIndex;
   
   public List12()
   {
      headNode = new Node<E>();
      prevNode = new Node<E>();
      nextNode = new Node<E>();
      selectedNode = new Node<E>();
   }
   public boolean add(E o)
   {
      Node<E> node = new Node<E>();
      node.setData(o);
      
      if (numNodes == 0)
      {
         selectedNode = headNode;
      }
      
      while (selectedNode.getNext() != null)
      {
         selectedNode = selectedNode.getNext();
      }
      
      selectedNode.setNext(node);
      numNodes++;
      
      if (selectedNode.getNext() == node)
      {
         addSuccess = true;
      }
      else
      {
         addSuccess = false;
      }
      return addSuccess;
      
   }
   public void add(int index, E element)
   {
      Node<E> node = new Node<E>();
      node.setData(element);
      
      selectedNode = headNode;
      
      if (index == numNodes)
      {
         this.add(element);
      }
      else
      {
         int i = 0;
         while (selectedNode.getNext() != null && i < index && index < numNodes)
         {
            if (i == 2)
            {
               prevNode = headNode.getNext();
            }
            selectedNode = selectedNode.getNext();
            prevNode = prevNode.getNext();
            i++;
         }
         nextNode = selectedNode.getNext();
         selectedNode.setNext(node);
         node.setNext(nextNode);
         numNodes++;
      }
   }
   public void clear()
   {
      Node<E> temp1Node = new Node<E>();
      Node<E> temp2Node = new Node<E>();
      temp1Node = headNode;
      temp2Node = temp1Node.getNext();
      while (numNodes > 0)
      {
         if (temp1Node.getNext() != null)
         {
            temp2Node = temp1Node.getNext();
            temp1Node.setNext(null);
            numNodes--;
         }
         if (temp2Node.getNext() != null)
         {
            temp1Node = temp2Node.getNext();
            temp2Node.setNext(null);
            numNodes--;
         }
      }
   }
   public boolean contains(Object o)
   {
      Node<E> tempNode = new Node<E>();
      tempNode = headNode;
      boolean contains = false;
      while (tempNode.getNext() != null)
      {
         tempNode = tempNode.getNext();
         if (tempNode.getData() == o)
         {
            contains = true;
         }
      }
      return contains;
   }
   public boolean equals(Object o)
   {
      boolean doesEqual = true;
      Iterator<E> iterate1, iterate2;
      try
      {
         iterate1 = this.iterator();
         iterate2 = ((List)o).iterator();

         while (iterate1.hasNext() == true && iterate2.hasNext() == true)
         {
            if (iterate1.next() != iterate2.next())
            {
               doesEqual = false;
            }
         }
         return doesEqual;
      }
      catch (Exception e)
      {
         return false;
      }
      
   }
   public E get(int index)
   {
      Node<E> tempNode = new Node<E>();
      tempNode = headNode;
      int i = 0;
      
      while (i < index + 1 && tempNode.getNext() != null )
      {
         tempNode = tempNode.getNext();
         i++;
      }
      return tempNode.getData();
      
   }
   public int hashCode()
   {
      int hashCode = 1;
      Iterator<E> i = this.iterator();
      while (i.hasNext()) 
      {
         Object obj = i.next();
         hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
      }
      return hashCode;
   }
   public int indexOf(Object o)
   {
      Node<E> tempNode = new Node<E>();
      int i = 0;
      int place = 0;
      tempNode = headNode;

      while (tempNode.getNext() != null)
      {
         tempNode = tempNode.getNext();
         if (tempNode.getData() == o)
         {
            place = i;
            return place;
         }
         else
         {
            i++;
         }
      }
      return -1;
   }
   public boolean isEmpty()
   {
      Boolean isEmpty = false;
      
      if (numNodes == 0)
      {
         isEmpty = true;
      }
      return isEmpty;
   }
   public Iterator<E> iterator()
   {
      return new LLIterator<E>();
   }
   public boolean remove(Object o)
   {
      Node<E> tempNode = new Node<E>();
      Boolean removedObject = false;
      tempNode = headNode;
      prevNode = headNode;
      
      int i = 0;
      while (tempNode.getNext() != null)
      {
         if (i > 0)
         {
            prevNode = prevNode.getNext();
         }
         tempNode = tempNode.getNext();
         
         if (tempNode.getData() == o)
         {
            prevNode.setNext(tempNode.getNext());
            removedObject = true;
            numNodes--;
         }
         i++;
      }
      return removedObject;
   }
   public E set(int index, E element)
   {
      Node<E> tempNode = new Node<E>();
      tempNode = headNode;
      int i = 0;
      
      while (i <= index && tempNode.getNext() != null )
      {
         tempNode = tempNode.getNext();
         i++;
      }
      E previousEl = tempNode.getData();
      tempNode.setData(element);
      
      return previousEl;
   }
   public int size()
   {
      return numNodes;
   }
   // no need to override
   public List12<E> subList(int x, int y){return new List12<E>();}
   public boolean addAll(Collection c) {return false;}
   public boolean addAll(int index, Collection c) {return false;}
   public boolean containsAll(Collection c){return false;}
   public int lastIndexOf(Object o){return 0;}
   public ListIterator listIterator(){return (ListIterator) new LLIterator<E>();}
   public ListIterator listIterator(int index){return (ListIterator) new LLIterator<E>();}
   public E remove(int index){return null;}
   public boolean removeAll(Collection c){return false;}
   public boolean retainAll(Collection c){return false;}
   public E[] toArray(){ return null;}
   public E[] toArray(Object[] a){ return null;}

   private static class Node<T>
   {
      private T data;
      private Node<T> next;
      
      public Node()
      {
         this.data = null;
         this.next = null;
      }
      public Node(T e, Node<T> nextElementPointer)
      {
         this.data = e;
         this.next = nextElementPointer;
      }
      public Node<T> getNext()
      {
         return this.next;
      }
      public T getData()
      {
         return this.data;
      }
      public void setNext(Node<T> nextNode)
      {
         this.next = nextNode;
      }
      public void setData(T e)
      {
         this.data = e;
      }
   }
   private class LLIterator<E> implements java.util.Iterator<E>
   {
      private Node<E> next;
      private Node<E> lastReturned;
      private Node<E> pred;
      
      public LLIterator()
      {
         lastReturned = (Node<E>) headNode;
         next = (Node<E>) headNode.getNext();
      }
      public boolean hasNext()
      {
         return next != null;
      }
      public E next()
      {
         if (next == null) throw new NoSuchElementException();
         pred = lastReturned;
         lastReturned = next;
         next = lastReturned.getNext();
         
         return lastReturned.data;
      }
      public void remove()
      {
         if (lastReturned == null) throw new IllegalStateException();
         pred.setNext(next);
         numNodes--;
         lastReturned = null;
      }
      public java.util.Iterator<E> iterator()
     {
        return new LLIterator();
     }

   }
}

