# data-structure-and-algorithm-analysis-in-java-3nd-edition

>

## 说明

> 代码章节划分 ch

### 编译运行

> javac -encoding UTF-8 ch00/Helloworld.java

> java ch00/Helloworld

> 0. 生成断点调试 
>
> javac -encoding UTF-8 -g ch00/Helloworld.java
>
> 1. java命令开启调试模式(在Terminal1中)
>
> java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=18000 -cp classes/ ch00.Helloworld
>
> 2. 
> jdb -connect com.sun.jdi.SocketAttach:hostname=localhost,port=18000
> <jdb -attach 18000 异常>
>
> 3. 设置断点 
>
> - run      // 运行程序
>
> - stop at ch00.Helloworld:12 // (在方法中用in 在行号上用at)
>
> - next      // next相当于执行一步方法
>
> - step      // step进入方法执行下一步
>
> - locals    // 会打印方法本地变量表
>
> - dump      // 查看对象的值
>
> - where all // 线程运行的位置, 告诉所有线程的当前运行位置

> jdb --help
```
选项无效: --help

用法: jdb <options> <class> <arguments>

其中, 选项包括:
    -help             输出此消息并退出
    -sourcepath <由 ";" 分隔的目录>
                      要在其中查找源文件的目录
    -attach <address>
                      使用标准连接器附加到指定地址处正在运行的 VM
    -listen <address>
                      等待正在运行的 VM 使用标准连接器在指定地址处连接
    -listenany
                      等待正在运行的 VM 使用标准连接器在任何可用地址处连接
    -launch
                      立即启动 VM 而不是等待 'run' 命令
    -listconnectors   列出此 VM 中的可用连接器
    -connect <connector-name>:<name1>=<value1>,...
                      使用所列参数值通过指定的连接器连接到目标 VM
    -dbgtrace [flags] 输出信息供调试jdb
    -tclient          在 HotSpot(TM) 客户机编译器中运行应用程序
    -tserver          在 HotSpot(TM) 服务器编译器中运行应用程序

转发到被调试进程的选项:
    -v -verbose[:class|gc|jni]
                      启用详细模式
    -D<name>=<value>  设置系统属性
    -classpath <由 ";" 分隔的目录>
                      列出要在其中查找类的目录
    -X<option>        非标准目标 VM 选项

<class> 是要开始调试的类的名称
<arguments> 是传递到 <class> 的 main() 方法的参数

要获得命令的帮助, 请在jdb提示下键入 'help'
```

```
** 命令列表 **
connectors                -- 列出此 VM 中可用的连接器和传输

run [class [args]]        -- 开始执行应用程序的主类        

threads [threadgroup]     -- 列出线程

thread <thread id>        -- 设置默认线程

suspend [thread id(s)]    -- 挂起线程 (默认值: all)        

resume [thread id(s)]     -- 恢复线程 (默认值: all)        

where [<thread id> | all] -- 转储线程的堆栈

wherei [<thread id> | all]-- 转储线程的堆栈, 以及 pc 信息  

up [n frames]             -- 上移线程的堆栈

down [n frames]           -- 下移线程的堆栈

kill <thread id> <expr>   -- 终止具有给定的异常错误对象的线程

interrupt <thread id>     -- 中断线程

print <expr>              -- 输出表达式的值

dump <expr>               -- 输出所有对象信息

eval <expr>               -- 对表达式求值 (与 print 相同)

set <lvalue> = <expr>     -- 向字段/变量/数组元素分配新值

locals                    -- 输出当前堆栈帧中的所有本地变量

classes                   -- 列出当前已知的类

class <class id>          -- 显示已命名类的详细资料

methods <class id>        -- 列出类的方法

fields <class id>         -- 列出类的字段

threadgroups              -- 列出线程组

threadgroup <name>        -- 设置当前线程组

stop in <class id>.<method>[(argument_type,...)] -- 在方法中设置断点

stop at <class id>:<line> -- 在行中设置断点

clear <class id>.<method>[(argument_type,...)] -- 清除方法中的断点

clear <class id>:<line>   -- 清除行中的断点

clear                     -- 列出断点

catch [uncaught|caught|all] <class id>|<class pattern>  -- 出现指定的异常错误时中断

ignore [uncaught|caught|all] <class id>|<class pattern> -- 对于指定的异常错误, 取消 'catch'

watch [access|all] <class id>.<field name>     -- 监视对字段的访问/修改

unwatch [access|all] <class id>.<field name>   -- 停止监视对字段的访问/修改
trace [go] methods [thread]
                          -- 跟踪方法进入和退出。
                          -- 除非指定 'go', 否则挂起所有线程

trace [go] method exit | exits [thread]
                          -- 跟踪当前方法的退出, 或者所有方法的退出
                          -- 除非指定 'go', 否则挂起所有线程

untrace [methods]         -- 停止跟踪方法进入和/或退出

step                      -- 执行当前行

step up                   -- 一直执行, 直到当前方法返回到其调用方

stepi                     -- 执行当前指令

next                      -- 步进一行 (步过调用)

cont                      -- 从断点处继续执行

list [line number|method] -- 输出源代码

use (或 sourcepath) [source file path]   -- 显示或更改源路径

exclude [<class pattern>, ... | "none"]  -- 对于指定的类, 不报告步骤或方法事件

classpath                 -- 从目标 VM 输出类路径信息

monitor <command>         -- 每次程序停止时执行命令

monitor                   -- 列出监视器

unmonitor <monitor#>      -- 删除监视器

read <filename>           -- 读取并执行命令文件

lock <expr>               -- 输出对象的锁信息

threadlocks [thread id]   -- 输出线程的锁信息

pop                       -- 通过当前帧出栈, 且包含当前帧

reenter                   -- 与 pop 相同, 但重新进入当前帧

redefine <class id> <class file name> -- 重新定义类的代码

disablegc <expr>          -- 禁止对象的垃圾收集

enablegc <expr>           -- 允许对象的垃圾收集

!!                        -- 重复执行最后一个命令

<n> <command>             -- 将命令重复执行 n 次
# <command>               -- 放弃 (无操作)
help (或 ?)               -- 列出命令
version                   -- 输出版本信息
exit (或 quit)            -- 退出调试器

<class id>: 带有程序包限定符的完整类名
<class pattern>: 带有前导或尾随通配符 ('*') 的类名
<thread id>: 'threads' 命令中报告的线程编号
<expr>: Java(TM) 编程语言表达式。
支持大多数常见语法。

可以将启动命令置于 "jdb.ini" 或 ".jdbrc" 中
位于 user.home 或 user.dir 中
```
