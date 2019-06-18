<#include "template.ftl">
<#macro title>Stack Exchange Search</#macro>

<#macro content>

<div class="container" style="padding-top: 20px;">

    <h3 class="content-subhead">Stack exchange search</h3>

    <form class="form-row" action="/search" method="get">
        <div class="col-4">
            <input name="searchString" <#if searchString??>value="${searchString}" </#if> type="text" class="form-control" placeholder="Enter search question">
        </div>
        <div class="col">
            <button type="submit" class="btn btn-outline-secondary"><i class="fa fa-search"></i>Search</button>
        </div>
    </form>

    <br>

    <#if questions??>

    <#if !questions?has_content>
        <div class="alert alert-danger" role="alert">
            No questions were found for this request.
        </div>
    <#else>
        <table class="table table-hover table-sm">
            <thead class="thead-dark">
            <tr>
                <th style="width:3%" scope="col">#</th>
                <th style="width:59%" scope="col">Title</th>
                <th style="width:14%" scope="col">Date</th>
                <th style="width:14%" scope="col">Author</th>
                <th style="width:10%" scope="col">Answers</th>
            </tr>
            </thead>
            <tbody id="table-body">
            <#list questions as question>
                <tr <#if question.answered>class="table-success"</#if> data-href="${question.link}">
                    <th scope="row">${question?index+1}</th>
                    <td>${question.title}</td>
                    <td>${question.creationDateString}</td>
                    <td>${question.author}</td>
                    <td>${question.answerCount}</td>
                </tr>
            </#list>
            </tbody>
        </table>

        <div id="loading" style="display:none;">
            Loading......
        </div>

    </#if>
    </#if>
    <input id="has_more" value="<#if questions??>${(questions?size == pageSize)?c}<#else>false</#if>" type="hidden">
    <input id="search-string" value="<#if searchString??>${searchString}</#if>" type="hidden">
</div>

</#macro>

<#macro scripts>
<script>
    var page_num=1;
    var pagesize=${pageSize};
    var has_more=$("#has_more").val() === 'true';

    jQuery(document).ready(function($) {
        $('*[data-href]').on('click', function() {
            window.open($(this).data("href"));
        });

        // add next page loading
        if (has_more) {
            $(window).scroll(swapQuestions);
        }
    });

    var swapQuestions= function(){
        //Check for user has reached bottom of page
        if ($(window).scrollTop() === ($(document).height() - window.innerHeight)) {
            $(window).off('scroll', swapQuestions);
            $('#loading').fadeIn();
            page_num++;
            ajaxGet();
            $('#loading').fadeOut();
        }

    };

    function ajaxGet(){
        $.ajax({
            type : "GET",
            url : "/search-json?searchString="+$("#search-string").val()+"&page="+page_num,
            success: function(questions){
                console.log("DATA: ", questions);

                for (var i=0; i<questions.length; i++){
                    var td;
                    var tr=document.createElement('tr');
                    tr.setAttribute("data-href", questions[i].link);

                    if (questions[i].answered) {
                        tr.setAttribute("class", "table-success");
                    }

                    var th = document.createElement('th');
                    th.innerHTML=(page_num-1)*pagesize+i+1;
                    th.setAttribute("scope", "row");
                    tr.appendChild(th);

                    var row_vals = [
                        questions[i].title,
                        questions[i].creationDateString,
                        questions[i].author,
                        questions[i].answerCount]

                    for (var j=0; j<row_vals.length; j++){
                        td = document.createElement('td');
                        td.innerHTML=row_vals[j];
                        tr.appendChild(td);
                    }

                    tr.onclick = function() {
                        window.open($(this).data("href"));
                    };

                    $('#table-body').append(tr);
                }

                if (questions.length === pagesize) {
                    $(window).scroll(swapQuestions);
                }
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
</script>
</#macro>