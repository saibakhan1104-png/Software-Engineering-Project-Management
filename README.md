 # Static VS Non Static

My goal is to count how many objects have been created, then the field count should normally be static, not non-static. The important point is that static does not mean the value will not increase. It means there is one shared copy of the variable for the entire class.

~~~
1. static variable:

If a variable is declared static, there is only one copy for the entire class.
All three objects share the same count.

Every time the constructor runs:count++;
the same shared variable increases.
So,Initially:  count = 0

s1 created → count = 1
s2 created → count = 2
s3 created → count = 3

This is why static is suitable for counting the total number of objects created.
~~~

~~~
2.Non-static variable

Now after removing static,
s1 → count = 1
s2 → count = 1
s3 → count = 1
Because every object has its own copy,So the non-static variable does not count the total number of objects.
