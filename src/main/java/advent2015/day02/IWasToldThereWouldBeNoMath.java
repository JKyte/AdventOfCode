package advent2015.day02;

import org.apache.commons.lang3.StringUtils;

public class IWasToldThereWouldBeNoMath {

    public IWasToldThereWouldBeNoMath() {
    }

    public int calculateWrappingOrders(String inputs) {
        int total = 0;
        for (String input : StringUtils.split(inputs, '\n')) {
            total += calculateWrappingOrder(input);
        }
        return total;
    }

    public int calculateWrappingOrder(String input) {
        String[] lwh = StringUtils.split(input, 'x');
        int l = Integer.parseInt(lwh[0]);
        int w = Integer.parseInt(lwh[1]);
        int h = Integer.parseInt(lwh[2]);
        return calculateWrappingOrder(l, w, h);
    }

    public int calculateWrappingOrder(int l, int w, int h) {
        int smallestSize = findSmallestSide(l, w, h);
        return (2 * l * w + 2 * w * h + 2 * h * l) + smallestSize;
    }

    private int findSmallestSide(int l, int w, int h) {
        int lw = l * w;
        int lh = l * h;
        int wh = w * h;
        return Math.min(lw, Math.min(lh, wh));
    }

    public int calculateRibbonOrders(String inputs) {
        int total = 0;
        for (String input : StringUtils.split(inputs, '\n')) {
            total += calculateRibbonOrder(input);
        }
        return total;
    }

    public int calculateRibbonOrder(String input) {
        String[] lwh = StringUtils.split(input, 'x');
        int l = Integer.parseInt(lwh[0]);
        int w = Integer.parseInt(lwh[1]);
        int h = Integer.parseInt(lwh[2]);
        return calculateRibbonOrder(l, w, h);
    }

    public int calculateRibbonOrder(int l, int w, int h) {
        int volume = l * w * h;
        int smallestPerimeter = findSmallestPerimeter(l, w, h);
        return smallestPerimeter + volume;
    }

    public int findSmallestPerimeter(int l, int w, int h) {
        int lw = l + l + w + w;
        int lh = l + l + h + h;
        int wh = w + w + h + h;
        return Math.min(lw, Math.min(lh, wh));
    }
}
