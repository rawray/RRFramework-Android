# RRFramework
- 一个Android应用开发框架。特点：可读性好，扩展性高，易用性强。

[![Build Status](https://travis-ci.org/rawray/RRFramework-Android.svg?branch=master)](https://travis-ci.org/rawray/RRFramework-Android)

## 项目架构
### 采用MVVM设计主体框架，通用代码写在框架中，业务和变化部分剥离。
- 横向分模块（UI, Service, Business, Model, Uitls, IO, Vendor...)
- 纵向分层次（interface layer， abstract layer, base layer, implement layer）

### UI
- Activity/Fragemnt业务分成封装，类图在doc目录下
- Activity/Fragment支持状态切换（加载页，内容页，空白页，网络错误页，其它错误页）
- 两套列表下拉刷新方案（Swipe，Custom）
- RecyclerView支持LoadMore，LoadMore支持手动和自动加载两种
- ...

### Service
纯服务，业务无关
- 数据管理
- 进程线程管理
- 线程队列管理
- 事件总线
- ...

### Business
通用业务
- 注册登录
- 分享
- 数据上报
- 版本更新
- ...
 
### Model
数据模型

### IO
数据的增删查改
- 适配层Adapter
- 缓存（包括内存，文件）Cache
- 文件File
- 数据库Database
- 网络Network

### Vendor
使用的第三放代码


## 代码规范
- Java类使用分隔线对代码进行分块，建议按以下顺序排列：
```java
    //----------------------- Absolute Methed ---------------------------------

    //----------------------- Override Methed ---------------------------------

    //----------------------- Static Methed -----------------------------------

    //----------------------- Public Methed -----------------------------------

    //----------------------- Protected Methed --------------------------------

    //----------------------- Private Methed ----------------------------------
```    
- 子类实现部分按从上到下进行分块实现，例如：
```java
    //----------------------- AbsFragment -------------------------------------
    @Override
    public void initContentView(View view) {
        super.initContentView(view);
        loadData();
    }

    //----------------------- AbsStatusFragment -------------------------------
    @Override
    protected void initStatusLayout() {
        super.initStatusLayout();
    }

    //----------------------- AbsRecyclerStatusFragment -----------------------
    @Override
    protected AbsRecyclerAdapter onCreateRecyclerAdapter() {
        return super.onCreateRecyclerAdapter();
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return super.onCreateLayoutManager();
    }

    //----------------------- AbsSwipeRecyclerStatusFragment -----------------------
    @Override
    public void onPullRefresh() {
           //TODO
    }

    @Override
    public void onLoadMore() {
           //TODO
    }
```

## 代码贡献者列表
- du_guo@sina.com  https://github.com/rawray
