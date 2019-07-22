import sys

rr = raw_input
rri = lambda: int(raw_input())
rrm = lambda: map(int, raw_input().split())

while True:
    try:
        line = sys.stdin.readline()
        if not line: break
        A = list(line)
        i = 0
        while i + 1 < len(A):
            if A[i] == A[i + 1] == '/': break
            if A[i] == '-' and A[i + 1] == '>':
                A[i] = '.'
                A[i + 1] = ''
            i += 1

        print
        "".join(A).rstrip('\n\r')
    except:
        break