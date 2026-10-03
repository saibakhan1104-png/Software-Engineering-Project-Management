# Multiple Threads with an Infinite Loop

I want to demonstrate multiple threads, an infinite loop, and then stop each thread after roughly 10 seconds.

~~~
This code creates 3 threads:Cooking,Washing,Cleaning

Each thread runs continuously using while (true), prints its task, sleeps for 1 second, and automatically stops after approximately 10 seconds. 


Inside run():This creates the loop:

while (true) {

while(true) means the loop is theoretically infinite.

But this condition eventually stops it:

if (System.currentTimeMillis() - startTime >= 10_000) {
    break;
}


So,the flow is:
Thread starts
     ↓
run()
     ↓
while(true)
     ↓
Print task
     ↓
sleep 1 second
     ↓
Check elapsed time
     ↓
10 seconds reached?
   ↙       ↘
 No        Yes
 ↓          ↓
Repeat     break
             ↓
       Thread finished

~~~
