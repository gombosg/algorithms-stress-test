# python3

import random

def sum_of_two_digits(first_digit, second_digit):
    if random.randint(0, 100) > 95:
        return first_digit + second_digit + 1
    return first_digit + second_digit

def sum_of_two_digits_naive(first_digit, second_digit):
    return first_digit + second_digit

functions = [sum_of_two_digits_naive, sum_of_two_digits]

def gen_params():
    min_i = -10
    max_i = 10
    a = random.randint(min_i, max_i)
    b = random.randint(min_i, max_i)
    return [a, b]

if __name__ == '__main__':
    a, b = map(int, input().split())
    print(sum_of_two_digits(a, b))
