#RRFramework
- 一个Android应用开发框架。特点：可读性好，扩展性高，易用性强。

##设计思路
- 采用MVVM设计主体框架(Model, View, ViewModel)，通用代码写在框架中，业务和变化部分剥离。
- 横向分模块（ui, common, entity, utils, notification, business, database, network ...)
- 纵向分层次（interface， abstract layer, base layer, implement layer）

##UI框架功能
- Activity/Fragemnt业务分成封装
- Activity/Fragment支持状态切换（加载页，内容页，空白页，网络错误页，其它错误页）
- 两套列表下拉刷新方案（Swipe，Custom）
- RecyclerView支持LoadMore，LoadMore支持手动和自动加载两种


##代码规范说明
- Java类使用分隔线对代码进行分块，建议按以下顺序排列：
```ruby
    //----------------------- Absolute Methed ---------------------------------

    //----------------------- Override Methed ---------------------------------

    //----------------------- Static Methed -----------------------------------

    //----------------------- Public Methed -----------------------------------

    //----------------------- Protected Methed --------------------------------

    //----------------------- Private Methed ----------------------------------
```    
- 子类实现部分按从上到下进行分块实现，例如：
```ruby
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


##界面动画
- 待添加

##代码贡献者列表
- [icon] du_guo@sina.com https://github.com/rawray

##TODO
- 

