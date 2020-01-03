import sys
from time import time
import APlusB as runner # only need to input file name here


def stress_test():
    print("Start")
    n = 0
    fs = runner.functions
    while True:
        results = []
        times = []
        data = runner.gen_params()
        for f in fs:
            try:
                t0 = time()
                results.append(f(*data))
                t1 = time()
                times.append(t1 - t0)
            except:
                print("Error! Data:", data)
                raise

        if all(x == results[0] for x in results):
            print("OK", n, "| Input:", "| Times/ratio:", *[[fs[l].__name__, f'{times[l]:.2f}', f'{times[l] / times[0]:.2f}'] for l in range(len(times))])
            n += 1
        else:
            print("Wrong answer", results, "Data:", data)
            break


stress_test()
