def compare(a, b):
    return int(a + b) - int(b + a)


def cmp_to_key(mycmp):
    'Convert a cmp= function into a key= function'

    class K:
        def __init__(self, obj, *args):
            self.obj = obj

        def __lt__(self, other):
            return mycmp(self.obj, other.obj) < 0

        def __gt__(self, other):
            return mycmp(self.obj, other.obj) > 0

        def __eq__(self, other):
            return mycmp(self.obj, other.obj) == 0

        def __le__(self, other):
            return mycmp(self.obj, other.obj) <= 0

        def __ge__(self, other):
            return mycmp(self.obj, other.obj) >= 0

        def __ne__(self, other):
            return mycmp(self.obj, other.obj) != 0

    return K


t = int(input())
primes = [0] * 100006
primes[0] = primes[1] = 1
for i in range(2, 100002):
    if primes[i] == 0:
        j = 2;
        while i * j <= 100001:
            primes[i * j] = 1
            j += 1
while t > 0:
    t -= 1
    n = int(input())
    ls = []
    arr = input().split()
    arr = list(map(int, arr))
    arr = list(map(str, arr))
    fl = 0
    for i in arr:
        if primes[int(i)] == 1:
            fl = 1
            ls.append(i)
    if fl == 0:
        print('No Secret Code!')
        continue

    ls.sort(reverse=True, key=cmp_to_key(compare))

    print(int(''.join(ls)))