public class Problem143 {
    
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        // Find mid point.
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second part of the list.
        ListNode prev = null;
        ListNode curr = slow;
        ListNode temp = null;
        while (curr != null) {
            temp = curr.next;

            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        // Merge two list. 
        while (prev.next != null) {
            temp = head.next;
            head.next = prev;
            head = temp;

            temp = prev.next;
            prev.next = head;
            prev = temp;
        }
    }
}