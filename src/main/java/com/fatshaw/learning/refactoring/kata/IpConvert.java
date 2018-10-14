package com.fatshaw.learning.refactoring.kata;

public class IpConvert {


    public static final int DISTANCE = 1;

    /**
     * The {@code Number} class represents number of each segment in ip address
     */
    private static class Number {

        public static final int MAX_NUMBER_OF_IP = 255;

        private long number = 0;

        public static Number getInstance() {
            return new Number();
        }

        public Number addNumber(int n) {
            number = number * 10 + n;
            if (number > MAX_NUMBER_OF_IP) {
                throw new IllegalArgumentException(String.format("%d is not a valid number in ip address", number));
            }
            return this;
        }

        public long getValueAndReset() {
            long tmp = number;
            number = 0;
            return tmp;
        }
    }

    /**
     * The {@code Result} class represents long value of ip address
     */
    private static class Result {

        public static final int LEFT_SHIFT_BIT_INIT = 24;
        public static final int LEFT_SHIFT_BIT = 8;

        private long result = 0;
        private int leftShiftBit = LEFT_SHIFT_BIT_INIT;

        public static Result getInstance() {
            return new Result();
        }

        public Result addNumber(long number) {

            result += number << leftShiftBit;
            leftShiftBit -= LEFT_SHIFT_BIT;
            return this;
        }

        public long getResult() {
            return result;
        }
    }

    /**
     * The {@code IpAddressStream} class represents stream of ip address with string format
     */
    private static class IpAddressStream {

        private String s;
        private int index;
        private char lastChar;

        public IpAddressStream(String s) {
            this.s = s;
        }

        public boolean isSpace() {
            return isSpace(getChar());
        }

        public boolean isDot() {
            return isDot(getChar());
        }


        public boolean isDidit() {
            return isDigit(getChar());
        }


        public char getChar() {
            if (index < s.length()) {
                char c = s.charAt(index);
                if (isValidChar(c)) {
                    return c;
                }
            }
            throw new ArrayIndexOutOfBoundsException();
        }

        private boolean isValidChar(char c) {
            if (isDigit(c) || isSpace(c) || isDot(c)) {
                return true;
            }
            throw new IllegalArgumentException(String.format("invalid character : %c", c));
        }

        public boolean isEnd() {
            return s == null || index >= s.length();
        }

        public IpAddressStream move(int distance) {
            lastChar = s.charAt(index);
            index += distance;
            return this;
        }

        /**
         * skip all the spaces until first char not space found
         */
        public IpAddressStream skipSpaces() {
            char c;
            while (index < s.length()) {
                c = s.charAt(index);
                if (isSpace(c)) {
                    index++;
                } else {
                    break;
                }
            }
            return this;
        }

        /**
         * check whether spaces are between digit and dot . if spaces are between digit and digit, throw exception
         */
        public IpAddressStream isValidFormat() {
            if (index < s.length()) {
                if (isDigit(lastChar) && isDigit(s.charAt(index))) {
                    throw new IllegalArgumentException("invalid format, spaces cannot between digits");
                }
            }
            return this;
        }

        private boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }

        private boolean isSpace(char c) {
            return c == ' ';
        }

        private boolean isDot(char c) {
            return c == '.';
        }

    }

    /**
     * convert ip address to long.
     */
    public static long convertIpToLong(String ipAddressString) {

        Result result = Result.getInstance();
        Number number = Number.getInstance();
        IpAddressStream ipAddressStream = new IpAddressStream(ipAddressString);

        while (!ipAddressStream.isEnd()) {
            if (ipAddressStream.isSpace()) {
                ipAddressStream.skipSpaces().isValidFormat();
            } else if (ipAddressStream.isDot()) {
                result.addNumber(number.getValueAndReset());
                ipAddressStream.move(DISTANCE);
            } else if (ipAddressStream.isDidit()) {
                number.addNumber(ipAddressStream.getChar() - '0');
                ipAddressStream.move(DISTANCE);
            }
        }

        return result.addNumber(number.getValueAndReset()).getResult();
    }

}
