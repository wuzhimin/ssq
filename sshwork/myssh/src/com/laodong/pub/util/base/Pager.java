package com.laodong.pub.util.base;

public class Pager {
    private int size = 0;
    private int length = 10;
    private int pagerOffSet = 1;
    private int begin = 0;
    private int end = 0;

    public Pager(){
    }    
    
		/**
		 * @return
		 */
		public int getLength()
		{
			return length;
		}

		/**
		 * @return
		 */
		public int getPagerOffSet()
		{
			return pagerOffSet;
		}

		/**
		 * @return
		 */
		public int getSize()
		{
			return size;
		}

		/**
		 * @param i
		 */
		public void setLength(int i)
		{
			length = i;
		}

		/**
		 * @param i
		 */
		public void setPagerOffSet(int i)
		{
			pagerOffSet = i;
		}

		/**
		 * @param i
		 */
		public void setSize(int i)
		{
			size = i;
		}
    
    public String toString(){
      return "pager:length="+length+";size="+size+";pagerOffSet="+pagerOffSet;
    }

		/**
		 * @return
		 */
		public int getBegin()
		{
			return begin;
		}

		/**
		 * @return
		 */
		public int getEnd()
		{
			return end;
		}

		/**
		 * @param i
		 */
		public void setBegin(int i)
		{
			begin = i;
		}

		/**
		 * @param i
		 */
		public void setEnd(int i)
		{
			end = i;
		}

}

