<el-form :inline="true" :model="submitData" class="demo-form-inline" :rules="rules" ref="ruleForm">
    <el-card class="box-card">
        <div slot="header" class="header clearfix">
            <span>report_posts</span>
            <el-button v-if="!ischeck && !isFind" class="fr" type="primary" @click="validate('ruleForm')">提交</el-button>
            <el-button v-else class="fr" type="primary" @click="goBack">返回</el-button>
        </div>
             <el-form-item label="p_id" prop="pId">
                 <el-input placeholder="请输入p_id" v-model="formData.pId"></el-input>
             </el-form-item>
    </el-card>
</el-form>