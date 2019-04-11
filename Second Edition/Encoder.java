package Encoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class Encoder {
	public static void main(String[] args) throws IOException {
		byte[] file = Files.readAllBytes((new File("Input.txt")).toPath());
		int[] frequency = new int[256];
		for (byte b : file) {
			frequency[b]++;
		}
		PriorityQueue<TreeNode<Byte>> priorityQueue = new PriorityQueue<TreeNode<Byte>>();
		for (int i = 0; i < 256; i++) {
			if (frequency[i] != 0) {
				priorityQueue.enqueue(new TreeNode<Byte>((byte) i), frequency[i]);
			}
		}
		while (priorityQueue.getSize() > 1) {
			int leftPrio = priorityQueue.lowestPriority();
			TreeNode<Byte> left = priorityQueue.dequeue();
			int rightPrio = priorityQueue.lowestPriority();
			TreeNode<Byte> right = priorityQueue.dequeue();
			priorityQueue.enqueue(new TreeNode<Byte>(left, right), leftPrio + rightPrio);
		}
		TreeNode<Byte> head = priorityQueue.dequeue();
		HashMap<Byte, String> map = new HashMap<Byte, String>();
		String tree = traverse(head, map, "");
		String res = "";
		for (byte b : file) {
			res += map.get(b);
		}
		int buffer = (8 - (res.length() % 8)) % 8;
		for (int i = 0; i < buffer; i++) {
			res += "0";
		}

		FileOutputStream fos = new FileOutputStream(new File("Output.MZIP"));
		fos.write("TEST.TXT\r\n".getBytes());
		fos.write(tree.getBytes());
		fos.write("\r\n".getBytes());
		fos.write(48 + buffer);
		fos.write("\r\n".getBytes());
		while (res.length() > 0) {
			fos.write(Integer.parseInt(res.substring(0, 8), 2));
			res = res.substring(8);
		}
	}

	private static String traverse(TreeNode<Byte> node, HashMap<Byte, String> map, String value) {
		if (node == null) {
			return "";
		} else if (node.isLeaf()) {
			map.put(node.getVal(), value);
			System.out.println(node.getVal()+": "+value);
			return "";
		} else {
			String out = "(";
			if (node.getLeft().isLeaf()) {
				out += node.getLeft().getVal().toString() + " ";
			}
			out += traverse(node.getLeft(), map, value + "0");
			out += traverse(node.getRight(), map, value + "1");
			if (node.getRight().isLeaf()) {
				out += node.getRight().getVal().toString();
			}
			out += ")";
			return out;
		}
	}
}
