双进程守护，解决安卓程序由于ndk崩溃不能重启的问题

## 主要还是使用了binder，使用了两个进程，通过binder的绑定关系来确定对方进程还在不在，如果不在就立即拉起，但是如果同时杀掉是守护不了的
